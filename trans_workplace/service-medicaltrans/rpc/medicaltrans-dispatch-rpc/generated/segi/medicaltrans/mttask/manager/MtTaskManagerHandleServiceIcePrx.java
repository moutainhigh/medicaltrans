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
// Generated from file `mt_task_manager.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.manager;

public interface MtTaskManagerHandleServiceIcePrx extends Ice.ObjectPrx
{
    public resp.RpcRespIce finishFixedTask(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce finishFixedTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_finishFixedTask __cb);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_finishFixedTask __cb);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_finishFixedTask(MtCommonIceParam mtCommonIceParam, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_finishFixedTask(Ice.AsyncResult __result);

    public resp.RpcRespIce autograph(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce autograph(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_autograph __cb);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_autograph __cb);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam, 
                                           IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam, 
                                           IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam, 
                                           java.util.Map<String, String> __ctx, 
                                           IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_autograph(MtCommonIceParam mtCommonIceParam, 
                                           java.util.Map<String, String> __ctx, 
                                           IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                           IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                           IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_autograph(Ice.AsyncResult __result);

    public resp.RpcRespIce evaluate(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce evaluate(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_evaluate __cb);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_evaluate __cb);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam, 
                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam, 
                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_evaluate(MtCommonIceParam mtCommonIceParam, 
                                          java.util.Map<String, String> __ctx, 
                                          IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                          IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                          IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_evaluate(Ice.AsyncResult __result);

    public MtTaskGrabRsp grabMtDispatchTask(MtCommonIceParam mtCommonIceParam);

    public MtTaskGrabRsp grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_grabMtDispatchTask __cb);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_grabMtDispatchTask __cb);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, 
                                                    IceInternal.Functional_GenericCallback1<MtTaskGrabRsp> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, 
                                                    IceInternal.Functional_GenericCallback1<MtTaskGrabRsp> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<MtTaskGrabRsp> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_grabMtDispatchTask(MtCommonIceParam mtCommonIceParam, 
                                                    java.util.Map<String, String> __ctx, 
                                                    IceInternal.Functional_GenericCallback1<MtTaskGrabRsp> __responseCb, 
                                                    IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                    IceInternal.Functional_BoolCallback __sentCb);

    public MtTaskGrabRsp end_grabMtDispatchTask(Ice.AsyncResult __result);

    public MtTaskApplyPersonLiableRsp applyPersonLiable(MtCommonIceParam mtCommonIceParam);

    public MtTaskApplyPersonLiableRsp applyPersonLiable(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_applyPersonLiable __cb);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_applyPersonLiable __cb);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam, 
                                                   IceInternal.Functional_GenericCallback1<MtTaskApplyPersonLiableRsp> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam, 
                                                   IceInternal.Functional_GenericCallback1<MtTaskApplyPersonLiableRsp> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<MtTaskApplyPersonLiableRsp> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_applyPersonLiable(MtCommonIceParam mtCommonIceParam, 
                                                   java.util.Map<String, String> __ctx, 
                                                   IceInternal.Functional_GenericCallback1<MtTaskApplyPersonLiableRsp> __responseCb, 
                                                   IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                   IceInternal.Functional_BoolCallback __sentCb);

    public MtTaskApplyPersonLiableRsp end_applyPersonLiable(Ice.AsyncResult __result);

    public resp.RpcRespIce backMtTask(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce backMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_backMtTask __cb);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_backMtTask __cb);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam, 
                                            IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam, 
                                            IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                            IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam, 
                                            java.util.Map<String, String> __ctx, 
                                            IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_backMtTask(MtCommonIceParam mtCommonIceParam, 
                                            java.util.Map<String, String> __ctx, 
                                            IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                            IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_backMtTask(Ice.AsyncResult __result);

    public resp.RpcRespIce startMtTask(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce startMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_startMtTask __cb);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_startMtTask __cb);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam, 
                                             IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam, 
                                             IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                             IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam, 
                                             java.util.Map<String, String> __ctx, 
                                             IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_startMtTask(MtCommonIceParam mtCommonIceParam, 
                                             java.util.Map<String, String> __ctx, 
                                             IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                             IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                             IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_startMtTask(Ice.AsyncResult __result);

    public resp.RpcRespIce cancelMtTask(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce cancelMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_cancelMtTask __cb);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_cancelMtTask __cb);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_cancelMtTask(MtCommonIceParam mtCommonIceParam, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_cancelMtTask(Ice.AsyncResult __result);

    public resp.RpcRespIce finishMtTask(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce finishMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_finishMtTask __cb);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_finishMtTask __cb);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_finishMtTask(MtCommonIceParam mtCommonIceParam, 
                                              java.util.Map<String, String> __ctx, 
                                              IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                              IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                              IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_finishMtTask(Ice.AsyncResult __result);

    public resp.RpcRespIce startTaskForWeb(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce startTaskForWeb(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_startTaskForWeb __cb);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_startTaskForWeb __cb);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_startTaskForWeb(MtCommonIceParam mtCommonIceParam, 
                                                 java.util.Map<String, String> __ctx, 
                                                 IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                 IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                 IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_startTaskForWeb(Ice.AsyncResult __result);

    public resp.RpcRespIce finishTaskForWeb(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce finishTaskForWeb(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_finishTaskForWeb __cb);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_finishTaskForWeb __cb);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam, 
                                                  IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam, 
                                                  IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_finishTaskForWeb(MtCommonIceParam mtCommonIceParam, 
                                                  java.util.Map<String, String> __ctx, 
                                                  IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                  IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                  IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_finishTaskForWeb(Ice.AsyncResult __result);

    public resp.RpcRespIce photograph(MtCommonIceParam mtCommonIceParam);

    public resp.RpcRespIce photograph(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam, Ice.Callback __cb);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam, Callback_MtTaskManagerHandleServiceIce_photograph __cb);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam, java.util.Map<String, String> __ctx, Callback_MtTaskManagerHandleServiceIce_photograph __cb);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam, 
                                            IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam, 
                                            IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                            IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam, 
                                            java.util.Map<String, String> __ctx, 
                                            IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_photograph(MtCommonIceParam mtCommonIceParam, 
                                            java.util.Map<String, String> __ctx, 
                                            IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                            IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                            IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_photograph(Ice.AsyncResult __result);
}
