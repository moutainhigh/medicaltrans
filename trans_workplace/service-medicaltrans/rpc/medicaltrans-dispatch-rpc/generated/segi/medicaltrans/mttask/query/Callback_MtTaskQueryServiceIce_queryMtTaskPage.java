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
// Generated from file `mt_task_query.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mttask.query;

public abstract class Callback_MtTaskQueryServiceIce_queryMtTaskPage
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<segi.medicaltrans.mttask.query.MtTaskPaginatorIce>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        MtTaskQueryServiceIcePrxHelper.__queryMtTaskPage_completed(this, __result);
    }
}
