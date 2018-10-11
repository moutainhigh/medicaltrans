package com.segi.uhomecp.medicaltrans.baseinfo.dbsource.rpc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.segi.uhomecp.medicaltrans.base.dbsourcerule.model.DbSourceRule;
import com.segi.uhomecp.medicaltrans.baseinfo.dbsource.service.MtDataSourceService;
import com.segi.uhomecp.rpc.RpcError;
import com.segi.uhomecp.wh.common.utils.CommonServiceUtils;

import Ice.Current;
import segi.datacachesvr.queryInfo.Organ;
import segi.medicaltrans.base.dbsource.DbSourceRuleIce;
import segi.medicaltrans.base.dbsource.DbSourceRuleRspIce;
import segi.medicaltrans.base.dbsource._MtDbSourceRuleServiceIceDisp;
@Component
public class MtDbSourceServiceRpc extends _MtDbSourceRuleServiceIceDisp {

	private static final long serialVersionUID = 1L;
	
	public static final Logger logger = LoggerFactory.getLogger(MtDbSourceServiceRpc.class);
	
	@Autowired
	private MtDataSourceService mtDataSourceService;

	@Override
	public DbSourceRuleRspIce getDbSourceRuleByGroupId(int groupOrganId, Current __current) {
		DbSourceRuleRspIce rst = new DbSourceRuleRspIce();
		rst.setCode(RpcError.SUCCESS.getCode());
		rst.setMessage(RpcError.SUCCESS.getMessage());
		
		try {
			
			logger.debug("查询数据源------------------------->groupOrganId{}"+groupOrganId);
			// 上线后需要 注释掉
			Organ organ = CommonServiceUtils.getOrganByID(groupOrganId);
			if(organ == null || organ.getParentOrganId() != 1) {
				rst.setCode(RpcError.FAIL.getCode());
				rst.setMessage("该网点不是一级物业Id，所以不能进行操作！！！");
				return rst;
			}
			////
			
			// 查询缓存
			DbSourceRule dbSource = mtDataSourceService.getDbSourceByGroupOrganIdRedis(groupOrganId);
			if (null == dbSource) {
				logger.debug("新建数据源------------------------->groupOrganId{}"+groupOrganId);
				// 缓存和表中查找不到数据
				dbSource = mtDataSourceService.saveOrGetNewDbSourceRuleAndSave(groupOrganId);
			}
			DbSourceRuleIce dbSourceRuleIce = new DbSourceRuleIce();
			dbSourceRuleIce.setDataSourceIdx(dbSource.getDbIdx());
			dbSourceRuleIce.setTableIdx(dbSource.getTableIdx());
			dbSourceRuleIce.setGroupId(String.valueOf(dbSource.getGroupOrganId()));
			rst.setDbSourceRuleIce(dbSourceRuleIce);
		} catch (Exception e) {
			rst.setCode(RpcError.FAIL.getCode());
			rst.setMessage(RpcError.FAIL.getMessage());
			logger.debug("getDbSourceRuleByGroupId{}=======>" + groupOrganId,e);
		}
		return rst;
	}
}
