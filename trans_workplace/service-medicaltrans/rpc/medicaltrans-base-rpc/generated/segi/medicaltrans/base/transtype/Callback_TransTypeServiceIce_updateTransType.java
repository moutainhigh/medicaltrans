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
// Generated from file `transtype.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.transtype;

public abstract class Callback_TransTypeServiceIce_updateTransType
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<resp.RpcRespIce>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        TransTypeServiceIcePrxHelper.__updateTransType_completed(this, __result);
    }
}
