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
// Generated from file `mt_transtype_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.common.transtype;

public abstract class Callback_ReportCommonTransTypeServiceIce_deleteTransTypeById
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<resp.RpcRespIce>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        ReportCommonTransTypeServiceIcePrxHelper.__deleteTransTypeById_completed(this, __result);
    }
}
