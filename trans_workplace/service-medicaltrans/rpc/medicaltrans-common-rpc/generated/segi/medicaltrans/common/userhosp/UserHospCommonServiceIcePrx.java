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
// Generated from file `mt_userhosp_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.userhosp;

public interface UserHospCommonServiceIcePrx extends Ice.ObjectPrx
{
    public UserHospRelReturnInfoIce queryHospUserInfo(UserHospIce userHospIce);

    public UserHospRelReturnInfoIce queryHospUserInfo(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce, Callback_UserHospCommonServiceIce_queryHospUserInfo __cb);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Callback_UserHospCommonServiceIce_queryHospUserInfo __cb);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRelReturnInfoIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRelReturnInfoIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRelReturnInfoIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryHospUserInfo(UserHospIce userHospIce, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRelReturnInfoIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public UserHospRelReturnInfoIce end_queryHospUserInfo(Ice.AsyncResult __result);

    public UserHospRelIceListRsp queryHospUserInfoList(UserHospIce userHospIce);

    public UserHospRelIceListRsp queryHospUserInfoList(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce, Callback_UserHospCommonServiceIce_queryHospUserInfoList __cb);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Callback_UserHospCommonServiceIce_queryHospUserInfoList __cb);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce, 
                                                       IceInternal.Functional_GenericCallback1<UserHospRelIceListRsp> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce, 
                                                       IceInternal.Functional_GenericCallback1<UserHospRelIceListRsp> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<UserHospRelIceListRsp> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryHospUserInfoList(UserHospIce userHospIce, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<UserHospRelIceListRsp> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public UserHospRelIceListRsp end_queryHospUserInfoList(Ice.AsyncResult __result);

    public UserIdsByHouseIdReturnIce queryUserIdsByHouseId(String houseId);

    public UserIdsByHouseIdReturnIce queryUserIdsByHouseId(String houseId, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId, Callback_UserHospCommonServiceIce_queryUserIdsByHouseId __cb);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId, java.util.Map<String, String> __ctx, Callback_UserHospCommonServiceIce_queryUserIdsByHouseId __cb);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId, 
                                                       IceInternal.Functional_GenericCallback1<UserIdsByHouseIdReturnIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId, 
                                                       IceInternal.Functional_GenericCallback1<UserIdsByHouseIdReturnIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<UserIdsByHouseIdReturnIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryUserIdsByHouseId(String houseId, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<UserIdsByHouseIdReturnIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public UserIdsByHouseIdReturnIce end_queryUserIdsByHouseId(Ice.AsyncResult __result);
}
