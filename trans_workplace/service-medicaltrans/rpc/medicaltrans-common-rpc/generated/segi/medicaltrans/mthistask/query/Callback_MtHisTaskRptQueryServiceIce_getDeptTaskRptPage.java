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
// Generated from file `mt_his_task_query.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mthistask.query;

public abstract class Callback_MtHisTaskRptQueryServiceIce_getDeptTaskRptPage
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<segi.medicaltrans.mthistask.query.MtTaskPaginatorIceRsp>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        MtHisTaskRptQueryServiceIcePrxHelper.__getDeptTaskRptPage_completed(this, __result);
    }
}
