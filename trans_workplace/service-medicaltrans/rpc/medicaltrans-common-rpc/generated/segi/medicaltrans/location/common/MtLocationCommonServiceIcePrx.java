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
// Generated from file `mt_location_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.location.common;

public interface MtLocationCommonServiceIcePrx extends Ice.ObjectPrx
{
    public LocationInfoListReturnIce getLocationInfoByRefIdList(int organId, java.util.List<java.lang.Integer> locationIdList);

    public LocationInfoListReturnIce getLocationInfoByRefIdList(int organId, java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, java.util.List<java.lang.Integer> locationIdList);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, java.util.List<java.lang.Integer> locationIdList, Ice.Callback __cb);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, java.util.List<java.lang.Integer> locationIdList, Callback_MtLocationCommonServiceIce_getLocationInfoByRefIdList __cb);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx, Callback_MtLocationCommonServiceIce_getLocationInfoByRefIdList __cb);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, 
                                                            java.util.List<java.lang.Integer> locationIdList, 
                                                            IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, 
                                                            java.util.List<java.lang.Integer> locationIdList, 
                                                            IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                            IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, 
                                                            java.util.List<java.lang.Integer> locationIdList, 
                                                            java.util.Map<String, String> __ctx, 
                                                            IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getLocationInfoByRefIdList(int organId, 
                                                            java.util.List<java.lang.Integer> locationIdList, 
                                                            java.util.Map<String, String> __ctx, 
                                                            IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                            IceInternal.Functional_BoolCallback __sentCb);

    public LocationInfoListReturnIce end_getLocationInfoByRefIdList(Ice.AsyncResult __result);

    public LocationInfoListReturnIce getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList);

    public LocationInfoListReturnIce getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, Ice.Callback __cb);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, Callback_MtLocationCommonServiceIce_getLocationInfoBylocationIdList __cb);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx, Callback_MtLocationCommonServiceIce_getLocationInfoBylocationIdList __cb);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, 
                                                                 IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, 
                                                                 IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                                 IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, 
                                                                 java.util.Map<String, String> __ctx, 
                                                                 IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getLocationInfoBylocationIdList(java.util.List<java.lang.Integer> locationIdList, 
                                                                 java.util.Map<String, String> __ctx, 
                                                                 IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                                 IceInternal.Functional_BoolCallback __sentCb);

    public LocationInfoListReturnIce end_getLocationInfoBylocationIdList(Ice.AsyncResult __result);

    public LocationInfoReturnIce getLocationInfoByMac(int organId, String mac);

    public LocationInfoReturnIce getLocationInfoByMac(int organId, String mac, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, String mac);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, String mac, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, String mac, Ice.Callback __cb);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, String mac, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, String mac, Callback_MtLocationCommonServiceIce_getLocationInfoByMac __cb);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, String mac, java.util.Map<String, String> __ctx, Callback_MtLocationCommonServiceIce_getLocationInfoByMac __cb);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, 
                                                      String mac, 
                                                      IceInternal.Functional_GenericCallback1<LocationInfoReturnIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, 
                                                      String mac, 
                                                      IceInternal.Functional_GenericCallback1<LocationInfoReturnIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                      IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, 
                                                      String mac, 
                                                      java.util.Map<String, String> __ctx, 
                                                      IceInternal.Functional_GenericCallback1<LocationInfoReturnIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getLocationInfoByMac(int organId, 
                                                      String mac, 
                                                      java.util.Map<String, String> __ctx, 
                                                      IceInternal.Functional_GenericCallback1<LocationInfoReturnIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                      IceInternal.Functional_BoolCallback __sentCb);

    public LocationInfoReturnIce end_getLocationInfoByMac(Ice.AsyncResult __result);

    public LocationInfoListReturn queryLocationInfoByOrganIdList(int organId);

    public LocationInfoListReturn queryLocationInfoByOrganIdList(int organId, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId, Callback_MtLocationCommonServiceIce_queryLocationInfoByOrganIdList __cb);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId, java.util.Map<String, String> __ctx, Callback_MtLocationCommonServiceIce_queryLocationInfoByOrganIdList __cb);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId, 
                                                                IceInternal.Functional_GenericCallback1<LocationInfoListReturn> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId, 
                                                                IceInternal.Functional_GenericCallback1<LocationInfoListReturn> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                                IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId, 
                                                                java.util.Map<String, String> __ctx, 
                                                                IceInternal.Functional_GenericCallback1<LocationInfoListReturn> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryLocationInfoByOrganIdList(int organId, 
                                                                java.util.Map<String, String> __ctx, 
                                                                IceInternal.Functional_GenericCallback1<LocationInfoListReturn> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                                IceInternal.Functional_BoolCallback __sentCb);

    public LocationInfoListReturn end_queryLocationInfoByOrganIdList(Ice.AsyncResult __result);

    public LocationInfoReturn queryLocationInfo(int organId, int locationId);

    public LocationInfoReturn queryLocationInfo(int organId, int locationId, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, int locationId);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, int locationId, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, int locationId, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, int locationId, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, int locationId, Callback_MtLocationCommonServiceIce_queryLocationInfo __cb);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, int locationId, java.util.Map<String, String> __ctx, Callback_MtLocationCommonServiceIce_queryLocationInfo __cb);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, 
                                                   int locationId, 
                                                   IceInternal.Functional_GenericCallback1<LocationInfoReturn> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, 
                                                   int locationId, 
                                                   IceInternal.Functional_GenericCallback1<LocationInfoReturn> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, 
                                                   int locationId, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<LocationInfoReturn> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryLocationInfo(int organId, 
                                                   int locationId, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<LocationInfoReturn> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public LocationInfoReturn end_queryLocationInfo(Ice.AsyncResult __result);

    public LocationInfoListReturn queryLocationList(java.util.List<java.lang.Integer> locationIdList);

    public LocationInfoListReturn queryLocationList(java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList, Callback_MtLocationCommonServiceIce_queryLocationList __cb);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx, Callback_MtLocationCommonServiceIce_queryLocationList __cb);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList, 
                                                   IceInternal.Functional_GenericCallback1<LocationInfoListReturn> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList, 
                                                   IceInternal.Functional_GenericCallback1<LocationInfoListReturn> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<LocationInfoListReturn> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryLocationList(java.util.List<java.lang.Integer> locationIdList, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<LocationInfoListReturn> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public LocationInfoListReturn end_queryLocationList(Ice.AsyncResult __result);

    public LocationInfoListReturnIce queryLocationInfoListByOrganId(int organId, java.util.List<java.lang.Integer> locationIdList);

    public LocationInfoListReturnIce queryLocationInfoListByOrganId(int organId, java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, java.util.List<java.lang.Integer> locationIdList);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, java.util.List<java.lang.Integer> locationIdList, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, java.util.List<java.lang.Integer> locationIdList, Callback_MtLocationCommonServiceIce_queryLocationInfoListByOrganId __cb);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, java.util.List<java.lang.Integer> locationIdList, java.util.Map<String, String> __ctx, Callback_MtLocationCommonServiceIce_queryLocationInfoListByOrganId __cb);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, 
                                                                java.util.List<java.lang.Integer> locationIdList, 
                                                                IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, 
                                                                java.util.List<java.lang.Integer> locationIdList, 
                                                                IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                                IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, 
                                                                java.util.List<java.lang.Integer> locationIdList, 
                                                                java.util.Map<String, String> __ctx, 
                                                                IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryLocationInfoListByOrganId(int organId, 
                                                                java.util.List<java.lang.Integer> locationIdList, 
                                                                java.util.Map<String, String> __ctx, 
                                                                IceInternal.Functional_GenericCallback1<LocationInfoListReturnIce> __responseCb, 
                                                                IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                                IceInternal.Functional_BoolCallback __sentCb);

    public LocationInfoListReturnIce end_queryLocationInfoListByOrganId(Ice.AsyncResult __result);

    public resp.RpcRespIce refreshRedisLocaiton();

    public resp.RpcRespIce refreshRedisLocaiton(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_refreshRedisLocaiton();

    public Ice.AsyncResult begin_refreshRedisLocaiton(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_refreshRedisLocaiton(Ice.Callback __cb);

    public Ice.AsyncResult begin_refreshRedisLocaiton(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_refreshRedisLocaiton(Callback_MtLocationCommonServiceIce_refreshRedisLocaiton __cb);

    public Ice.AsyncResult begin_refreshRedisLocaiton(java.util.Map<String, String> __ctx, Callback_MtLocationCommonServiceIce_refreshRedisLocaiton __cb);

    public Ice.AsyncResult begin_refreshRedisLocaiton(IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_refreshRedisLocaiton(IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                      IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_refreshRedisLocaiton(java.util.Map<String, String> __ctx, 
                                                      IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_refreshRedisLocaiton(java.util.Map<String, String> __ctx, 
                                                      IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                      IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_refreshRedisLocaiton(Ice.AsyncResult __result);

    public resp.RpcRespIce refreshRedisUserLocaiton();

    public resp.RpcRespIce refreshRedisUserLocaiton(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_refreshRedisUserLocaiton();

    public Ice.AsyncResult begin_refreshRedisUserLocaiton(java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_refreshRedisUserLocaiton(Ice.Callback __cb);

    public Ice.AsyncResult begin_refreshRedisUserLocaiton(java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_refreshRedisUserLocaiton(Callback_MtLocationCommonServiceIce_refreshRedisUserLocaiton __cb);

    public Ice.AsyncResult begin_refreshRedisUserLocaiton(java.util.Map<String, String> __ctx, Callback_MtLocationCommonServiceIce_refreshRedisUserLocaiton __cb);

    public Ice.AsyncResult begin_refreshRedisUserLocaiton(IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_refreshRedisUserLocaiton(IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                          IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_refreshRedisUserLocaiton(java.util.Map<String, String> __ctx, 
                                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_refreshRedisUserLocaiton(java.util.Map<String, String> __ctx, 
                                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                          IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_refreshRedisUserLocaiton(Ice.AsyncResult __result);
}
