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
// Generated from file `mt_task_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.common;

public interface MtTaskCommonServiceIcePrx extends Ice.ObjectPrx
{
    public resp.RpcRespIce updateTaskUnusalClose(int groupOrganId, java.util.List<java.lang.Integer> organIdList, long limitDate);

    public resp.RpcRespIce updateTaskUnusalClose(int groupOrganId, java.util.List<java.lang.Integer> organIdList, long limitDate, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, java.util.List<java.lang.Integer> organIdList, long limitDate);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, java.util.List<java.lang.Integer> organIdList, long limitDate, java.util.Map<String, String> __ctx);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, java.util.List<java.lang.Integer> organIdList, long limitDate, Ice.Callback __cb);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, java.util.List<java.lang.Integer> organIdList, long limitDate, java.util.Map<String, String> __ctx, Ice.Callback __cb);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, java.util.List<java.lang.Integer> organIdList, long limitDate, Callback_MtTaskCommonServiceIce_updateTaskUnusalClose __cb);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, java.util.List<java.lang.Integer> organIdList, long limitDate, java.util.Map<String, String> __ctx, Callback_MtTaskCommonServiceIce_updateTaskUnusalClose __cb);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, 
                                                       java.util.List<java.lang.Integer> organIdList, 
                                                       long limitDate, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, 
                                                       java.util.List<java.lang.Integer> organIdList, 
                                                       long limitDate, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, 
                                                       java.util.List<java.lang.Integer> organIdList, 
                                                       long limitDate, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb);

    public Ice.AsyncResult begin_updateTaskUnusalClose(int groupOrganId, 
                                                       java.util.List<java.lang.Integer> organIdList, 
                                                       long limitDate, 
                                                       java.util.Map<String, String> __ctx, 
                                                       IceInternal.Functional_GenericCallback1<resp.RpcRespIce> __responseCb, 
                                                       IceInternal.Functional_GenericCallback1<Ice.Exception> __exceptionCb, 
                                                       IceInternal.Functional_BoolCallback __sentCb);

    public resp.RpcRespIce end_updateTaskUnusalClose(Ice.AsyncResult __result);
}
