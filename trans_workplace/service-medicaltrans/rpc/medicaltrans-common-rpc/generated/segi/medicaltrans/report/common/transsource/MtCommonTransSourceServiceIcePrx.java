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
// Generated from file `mt_transsource_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.common.transsource;

public interface MtCommonTransSourceServiceIcePrx extends Ice.ObjectPrx
{
    public resp.RpcRespIce saveTransSource(TransSourceInfo transSourceInfo);

    public resp.RpcRespIce saveTransSource(TransSourceInfo transSourceInfo, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo, Ice.Callback __cb);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo, Callback_MtCommonTransSourceServiceIce_saveTransSource __cb);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo, java.util.Map<String, String> __ctx, Callback_MtCommonTransSourceServiceIce_saveTransSource __cb);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_saveTransSource(TransSourceInfo transSourceInfo, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_saveTransSource(Ice.AsyncResult __result);

    public resp.RpcRespIce updateTransSourceById(int id, TransSourceInfo transSourceInfo);

    public resp.RpcRespIce updateTransSourceById(int id, TransSourceInfo transSourceInfo, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_updateTransSourceById(int id, TransSourceInfo transSourceInfo);

    public Ice.AsyncResult begin_updateTransSourceById(int id, TransSourceInfo transSourceInfo, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_updateTransSourceById(int id, TransSourceInfo transSourceInfo, Ice.Callback __cb);

    public Ice.AsyncResult begin_updateTransSourceById(int id, TransSourceInfo transSourceInfo, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_updateTransSourceById(int id, TransSourceInfo transSourceInfo, Callback_MtCommonTransSourceServiceIce_updateTransSourceById __cb);

    public Ice.AsyncResult begin_updateTransSourceById(int id, TransSourceInfo transSourceInfo, java.util.Map<String, String> __ctx, Callback_MtCommonTransSourceServiceIce_updateTransSourceById __cb);

    public Ice.AsyncResult begin_updateTransSourceById(int id, 
                                                       TransSourceInfo transSourceInfo, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_updateTransSourceById(int id, 
                                                       TransSourceInfo transSourceInfo, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_updateTransSourceById(int id, 
                                                       TransSourceInfo transSourceInfo, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_updateTransSourceById(int id, 
                                                       TransSourceInfo transSourceInfo, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_updateTransSourceById(Ice.AsyncResult __result);

    public resp.RpcRespIce deleteTransSourceById(int id);

    public resp.RpcRespIce deleteTransSourceById(int id, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_deleteTransSourceById(int id);

    public Ice.AsyncResult begin_deleteTransSourceById(int id, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_deleteTransSourceById(int id, Ice.Callback __cb);

    public Ice.AsyncResult begin_deleteTransSourceById(int id, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_deleteTransSourceById(int id, Callback_MtCommonTransSourceServiceIce_deleteTransSourceById __cb);

    public Ice.AsyncResult begin_deleteTransSourceById(int id, java.util.Map<String, String> __ctx, Callback_MtCommonTransSourceServiceIce_deleteTransSourceById __cb);

    public Ice.AsyncResult begin_deleteTransSourceById(int id, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_deleteTransSourceById(int id, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_deleteTransSourceById(int id, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_deleteTransSourceById(int id, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_deleteTransSourceById(Ice.AsyncResult __result);

    public TransSourceInfoReturnIce getTransSourceById(int id);

    public TransSourceInfoReturnIce getTransSourceById(int id, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getTransSourceById(int id);

    public Ice.AsyncResult begin_getTransSourceById(int id, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_getTransSourceById(int id, Ice.Callback __cb);

    public Ice.AsyncResult begin_getTransSourceById(int id, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_getTransSourceById(int id, Callback_MtCommonTransSourceServiceIce_getTransSourceById __cb);

    public Ice.AsyncResult begin_getTransSourceById(int id, java.util.Map<String, String> __ctx, Callback_MtCommonTransSourceServiceIce_getTransSourceById __cb);

    public Ice.AsyncResult begin_getTransSourceById(int id, 
                                                    IceInternal.Functional_GenericCallback1<TransSourceInfoReturnIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getTransSourceById(int id, 
                                                    IceInternal.Functional_GenericCallback1<TransSourceInfoReturnIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_getTransSourceById(int id, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<TransSourceInfoReturnIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_getTransSourceById(int id, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<TransSourceInfoReturnIce> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public TransSourceInfoReturnIce end_getTransSourceById(Ice.AsyncResult __result);
}
