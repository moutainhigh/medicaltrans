// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.6.3
//
// <auto-generated>
//
// Generated from file `mt_location_manager.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.location;

public interface _MtLocationManagerServiceIceOperationsNC
{
    resp.RpcRespIce saveMtBuildLocation(MtBuildLocationIceParam mtBuildLocationIceParam);

    MtBuildLocationDetailReturnIce queryMtBuildLocationDetail(String locationId);

    MtBuildLocationDetailReturnIce getLocationInfoByMac(MtBuildLocationIceParam mtBuildLocationIceParam);

    resp.RpcRespIce updateMtBuildLocation(MtBuildLocationIceParam mtBuildLocationIceParam);

    resp.RpcRespIce deleteMtBuildLocation(MtBuildLocationIceParam mtBuildLocationIceParam);

    LocationInfoPaginatorIce queryLocationPageByFloorId(MtBuildLocationIceParam mtBuildLocationIceParam);

    LocationInfoListReturnIce queryLocationListByFloorId(MtBuildLocationIceParam mtBuildLocationIceParam);

    LocationInfoPaginatorIce queryLocationPageByOrgName(MtBuildLocationQueryIceParam mtBuildLocationQueryIceParam);

    LocationInfoPaginatorIce queryLocationPageByOrgNameDefault(MtBuildLocationIceParam mtBuildLocationIceParam);

    String queryLocationByOrganIdRedis(String organId);
}