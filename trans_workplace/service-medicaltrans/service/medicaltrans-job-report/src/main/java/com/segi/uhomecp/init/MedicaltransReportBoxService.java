package com.segi.uhomecp.init;

import java.util.Map;

import segi.medicaltrans.report.repairdata.RepairDataServiceIce;
import Ice.Object;
import IceExt.AbstractIceBoxService;

import com.google.common.collect.Maps;
import com.segi.uhomecp.utils.SpringContextUtils;

public class MedicaltransReportBoxService extends AbstractIceBoxService {

  @Override
  public Map<String, Object> createIceServiceObj(String[] args) {
    SpringContextUtils.instance();

    Map<String, Object> resMap = Maps.newHashMap();
    
    RepairDataServiceIce repairDataServiceIce = SpringContextUtils.getBean(RepairDataServiceIce.class);
	resMap.put(RepairDataServiceIce.class.getName(), repairDataServiceIce);
    return resMap;
  }
}
