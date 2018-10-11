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
// Generated from file `mt_userhosp.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.userhosp;

public interface UserHospServiceIcePrx extends Ice.ObjectPrx
{
    public UserHospRelReturnIce queryUserHospInfo(UserHospIce userHospIce);

    public UserHospRelReturnIce queryUserHospInfo(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce, Callback_UserHospServiceIce_queryUserHospInfo __cb);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Callback_UserHospServiceIce_queryUserHospInfo __cb);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRelReturnIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRelReturnIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRelReturnIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryUserHospInfo(UserHospIce userHospIce, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRelReturnIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public UserHospRelReturnIce end_queryUserHospInfo(Ice.AsyncResult __result);

    public resp.RpcRespIce saveUserHospInfo(UserHospParamIce userHospParamIce);

    public resp.RpcRespIce saveUserHospInfo(UserHospParamIce userHospParamIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce, Ice.Callback __cb);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce, Callback_UserHospServiceIce_saveUserHospInfo __cb);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce, java.util.Map<String, String> __ctx, Callback_UserHospServiceIce_saveUserHospInfo __cb);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce, 
                                                  IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce, 
                                                  IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_saveUserHospInfo(UserHospParamIce userHospParamIce, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_saveUserHospInfo(Ice.AsyncResult __result);

    public UserHospRelReturnPadIce queryHospUserPad(UserHospIce userHospIce);

    public UserHospRelReturnPadIce queryHospUserPad(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce, Callback_UserHospServiceIce_queryHospUserPad __cb);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Callback_UserHospServiceIce_queryHospUserPad __cb);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce, 
                                                  IceInternal.Functional_GenericCallback1<UserHospRelReturnPadIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce, 
                                                  IceInternal.Functional_GenericCallback1<UserHospRelReturnPadIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_GenericCallback1<UserHospRelReturnPadIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryHospUserPad(UserHospIce userHospIce, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_GenericCallback1<UserHospRelReturnPadIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb);

    public UserHospRelReturnPadIce end_queryHospUserPad(Ice.AsyncResult __result);

    public resp.RpcRespIce switchoverUserHosp(UserHospIce userHospIce);

    public resp.RpcRespIce switchoverUserHosp(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce, Ice.Callback __cb);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce, Callback_UserHospServiceIce_switchoverUserHosp __cb);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Callback_UserHospServiceIce_switchoverUserHosp __cb);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_switchoverUserHosp(UserHospIce userHospIce, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_switchoverUserHosp(Ice.AsyncResult __result);

    public UserHospRetPageIce queryUserHospPage(UserHospIce userHospIce);

    public UserHospRetPageIce queryUserHospPage(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce, Callback_UserHospServiceIce_queryUserHospPage __cb);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce, java.util.Map<String, String> __ctx, Callback_UserHospServiceIce_queryUserHospPage __cb);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRetPageIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRetPageIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRetPageIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_queryUserHospPage(UserHospIce userHospIce, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<UserHospRetPageIce> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public UserHospRetPageIce end_queryUserHospPage(Ice.AsyncResult __result);
}