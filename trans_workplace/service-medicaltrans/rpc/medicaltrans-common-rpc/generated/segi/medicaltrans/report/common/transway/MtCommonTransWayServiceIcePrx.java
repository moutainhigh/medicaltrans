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
// Generated from file `mt_transway_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.common.transway;

public interface MtCommonTransWayServiceIcePrx extends Ice.ObjectPrx
{
    public resp.RpcRespIce saveTransWay(TransWayInfo transWayInfo);

    public resp.RpcRespIce saveTransWay(TransWayInfo transWayInfo, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, Ice.Callback __cb);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, Callback_MtCommonTransWayServiceIce_saveTransWay __cb);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, Callback_MtCommonTransWayServiceIce_saveTransWay __cb);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_saveTransWay(TransWayInfo transWayInfo, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_saveTransWay(Ice.AsyncResult __result);

    public resp.RpcRespIce updateTransWayById(int id, TransWayInfo transWayInfo);

    public resp.RpcRespIce updateTransWayById(int id, TransWayInfo transWayInfo, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo);

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, Ice.Callback __cb);

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, Callback_MtCommonTransWayServiceIce_updateTransWayById __cb);

    public Ice.AsyncResult begin_updateTransWayById(int id, TransWayInfo transWayInfo, java.util.Map<String, String> __ctx, Callback_MtCommonTransWayServiceIce_updateTransWayById __cb);

    public Ice.AsyncResult begin_updateTransWayById(int id, 
                                                    TransWayInfo transWayInfo, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_updateTransWayById(int id, 
                                                    TransWayInfo transWayInfo, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_updateTransWayById(int id, 
                                                    TransWayInfo transWayInfo, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_updateTransWayById(int id, 
                                                    TransWayInfo transWayInfo, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_updateTransWayById(Ice.AsyncResult __result);

    public resp.RpcRespIce deleteTransWayById(int id);

    public resp.RpcRespIce deleteTransWayById(int id, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_deleteTransWayById(int id);

    public Ice.AsyncResult begin_deleteTransWayById(int id, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_deleteTransWayById(int id, Ice.Callback __cb);

    public Ice.AsyncResult begin_deleteTransWayById(int id, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_deleteTransWayById(int id, Callback_MtCommonTransWayServiceIce_deleteTransWayById __cb);

    public Ice.AsyncResult begin_deleteTransWayById(int id, java.util.Map<String, String> __ctx, Callback_MtCommonTransWayServiceIce_deleteTransWayById __cb);

    public Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_deleteTransWayById(int id, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_deleteTransWayById(Ice.AsyncResult __result);

    public TransWayInfoReturnIce getTransWayById(int id);

    public TransWayInfoReturnIce getTransWayById(int id, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getTransWayById(int id);

    public Ice.AsyncResult begin_getTransWayById(int id, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getTransWayById(int id, Ice.Callback __cb);

    public Ice.AsyncResult begin_getTransWayById(int id, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getTransWayById(int id, Callback_MtCommonTransWayServiceIce_getTransWayById __cb);

    public Ice.AsyncResult begin_getTransWayById(int id, java.util.Map<String, String> __ctx, Callback_MtCommonTransWayServiceIce_getTransWayById __cb);

    public Ice.AsyncResult begin_getTransWayById(int id, 
                                                 IceInternal.Functional_GenericCallback1<TransWayInfoReturnIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getTransWayById(int id, 
                                                 IceInternal.Functional_GenericCallback1<TransWayInfoReturnIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_getTransWayById(int id, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<TransWayInfoReturnIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getTransWayById(int id, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<TransWayInfoReturnIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public TransWayInfoReturnIce end_getTransWayById(Ice.AsyncResult __result);
}
