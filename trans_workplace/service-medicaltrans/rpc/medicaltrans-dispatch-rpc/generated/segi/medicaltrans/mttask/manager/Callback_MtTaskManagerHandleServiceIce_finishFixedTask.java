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

public abstract class Callback_MtTaskManagerHandleServiceIce_finishFixedTask
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<resp.RpcRespIce>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        MtTaskManagerHandleServiceIcePrxHelper.__finishFixedTask_completed(this, __result);
    }
}
