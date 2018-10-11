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
// Generated from file `pushjob.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.whcommon.push;

public interface PushServiceIcePrx extends Ice.ObjectPrx
{
    public resp.RpcRespIce push(MessageIce messageIce);

    public resp.RpcRespIce push(MessageIce messageIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_push(MessageIce messageIce);

    public Ice.AsyncResult begin_push(MessageIce messageIce, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_push(MessageIce messageIce, Ice.Callback __cb);

    public Ice.AsyncResult begin_push(MessageIce messageIce, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_push(MessageIce messageIce, Callback_PushServiceIce_push __cb);

    public Ice.AsyncResult begin_push(MessageIce messageIce, java.util.Map<String, String> __ctx, Callback_PushServiceIce_push __cb);

    public Ice.AsyncResult begin_push(MessageIce messageIce, 
                                      IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_push(MessageIce messageIce, 
                                      IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                      IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_push(MessageIce messageIce, 
                                      java.util.Map<String, String> __ctx, 
                                      IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_push(MessageIce messageIce, 
                                      java.util.Map<String, String> __ctx, 
                                      IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                      IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                      IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_push(Ice.AsyncResult __result);

    public resp.RpcRespIce pushList(java.util.List<MessageIce> list);

    public resp.RpcRespIce pushList(java.util.List<MessageIce> list, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list, Ice.Callback __cb);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list, Callback_PushServiceIce_pushList __cb);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list, java.util.Map<String, String> __ctx, Callback_PushServiceIce_pushList __cb);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list, 
                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list, 
                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_pushList(java.util.List<MessageIce> list, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_pushList(Ice.AsyncResult __result);

    public resp.RpcRespIce delete(String businessKey, String msgTypeCode);

    public resp.RpcRespIce delete(String businessKey, String msgTypeCode, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_delete(String businessKey, String msgTypeCode);

    public Ice.AsyncResult begin_delete(String businessKey, String msgTypeCode, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_delete(String businessKey, String msgTypeCode, Ice.Callback __cb);

    public Ice.AsyncResult begin_delete(String businessKey, String msgTypeCode, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_delete(String businessKey, String msgTypeCode, Callback_PushServiceIce_delete __cb);

    public Ice.AsyncResult begin_delete(String businessKey, String msgTypeCode, java.util.Map<String, String> __ctx, Callback_PushServiceIce_delete __cb);

    public Ice.AsyncResult begin_delete(String businessKey, 
                                        String msgTypeCode, 
                                        IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_delete(String businessKey, 
                                        String msgTypeCode, 
                                        IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_delete(String businessKey, 
                                        String msgTypeCode, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_delete(String businessKey, 
                                        String msgTypeCode, 
                                        java.util.Map<String, String> __ctx, 
                                        IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                        IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                        IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_delete(Ice.AsyncResult __result);
}