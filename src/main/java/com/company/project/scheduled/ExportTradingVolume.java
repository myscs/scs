package com.company.project.scheduled;


import com.company.project.model.TradingVolume;
import com.company.project.service.CustomerRelationsService;
import com.company.project.service.TradingVolumeService;
import com.company.project.utils.DtUtils;
import com.company.project.utils.StrUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

;

/**
 * Created by huangyelong on 2020/3/30.
 */
@Component
public class ExportTradingVolume {
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public final static long ONE_Minute =  60 * 1000;
    public final static long half_of_hour=  1800 * 1000;

   @Value("${excel.path.source}")
   private String excelPathSource;


    @Value("${excel.path.bak}")
    private String excelPathBak;


    @Value("${excel.path.error}")
    private String excelPathError;

    @Resource
    private CustomerRelationsService customerRelationsService;



    @Resource
    private TradingVolumeService tradingVolumeService;

    @Scheduled(fixedDelay=half_of_hour)
    public void exportTradingVolume() throws Exception{
        logger.info(DtUtils.getDateYYMMDDHHMMSSS()+" >>exportTradingVolume执行开始....");
        File file = new File(excelPathSource);
        File[] fileList = file.listFiles();
        if(fileList ==null){
            return;
        }
        for (File excelFile:fileList) {
            String fileName=excelFile.getName();
            if(fileName.endsWith("xls") || fileName.endsWith("xlsx")){
                try {
                    exportExcel(excelFile.getAbsolutePath());
                    excelFile.renameTo(new File(excelPathBak+File.separator+DtUtils.getDateYYMMDDHHMMSSS()+fileName));

                }catch (Exception e){
                    logger.error("处理文件异常："+fileName+e.getMessage());
                    excelFile.renameTo(new File(excelPathError+File.separator+DtUtils.getDateYYMMDDHHMMSSS()+fileName));

                }
            }
        }

        logger.info(DtUtils.getDateYYMMDDHHMMSSS()+" >>exportTradingVolume执行结束....");
    }


    @Transactional
    public  void exportExcel(String s) {
        Workbook wb =null;
        Sheet sheet = null;
        Row row = null;
        List<Map<String,String>> list = null;
        String cellData = null;
        String filePath = s;
        String columns[] = {"ci_no","ci_name","open_date","trading_volume","total_assets","stock_value","fund_balance","net_commission","statistical_date","agent_name"};
        wb = readExcel(filePath);
        if(wb != null){
            //用来存放表中数据
            list = new ArrayList<Map<String,String>>();
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i<rownum; i++) {
                Map<String,String> map = new LinkedHashMap<String,String>();
                row = sheet.getRow(i);
                if(row !=null){
                    for (int j=0;j<colnum;j++){
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        map.put(columns[j], cellData);
                    }
                }else{
                    break;
                }
                list.add(map);
            }
        }
        //遍历解析出来的list
        for (Map<String,String> map : list) {
            if(StringUtils.isBlank(map.get("ci_no"))){
                continue;
            }
            TradingVolume tradingVolume=new TradingVolume();
            tradingVolume.setCiNo(map.get("ci_no"));

            tradingVolume.setCiName(map.get("ci_name"));
            tradingVolume.setStatisticalDate(map.get("statistical_date"));
            if(StringUtils.isBlank(tradingVolume.getStatisticalDate())){
                tradingVolume.setStatisticalDate(DtUtils.getDateYYMMDD());
            }
            tradingVolume.setOpenDate(StrUtils.strToDate(map.get("open_date")));
            tradingVolume.setTradingVolume(StrUtils.strToDecimal(map.get("trading_volume")));
            tradingVolume.setTotalAssets(StrUtils.strToDecimal(map.get("total_assets")));
            tradingVolume.setStockValue(StrUtils.strToDecimal(map.get("stock_value")));
            tradingVolume.setFundBalance(StrUtils.strToDecimal(map.get("fund_balance")));
            tradingVolume.setNetCommission(StrUtils.strToDecimal(map.get("net_commission")));
            tradingVolume.setAgentName(map.get("agent_name"));
            tradingVolume.setStatus("W");

            Condition condition= new Condition(TradingVolume.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("ciNo",tradingVolume.getCiNo());
            criteria.andEqualTo("statisticalDate",tradingVolume.getStatisticalDate());
            List<TradingVolume> tradingVolumeList=tradingVolumeService.findByCondition(condition);
            if(CollectionUtils.isEmpty(tradingVolumeList)) {
                tradingVolumeService.save(tradingVolume);
            }else{
                logger.warn("data is exist:"+tradingVolume.toString());
            }

        }

    }
    //读取excel
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case Cell.CELL_TYPE_NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }


//    @Scheduled(cron="0 15 3 * * ?")
//    public void cronJob(){
//        System.out.println(DtUtils.getDateYYMMDDHHMMSSS()+" >>cron执行....");
//    }
}
