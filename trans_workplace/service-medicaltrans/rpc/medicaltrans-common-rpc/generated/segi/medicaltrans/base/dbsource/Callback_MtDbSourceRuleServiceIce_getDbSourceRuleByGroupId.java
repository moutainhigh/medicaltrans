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
// Generated from file `mt_db_source.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.dbsource;

public abstract class Callback_MtDbSourceRuleServiceIce_getDbSourceRuleByGroupId
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<segi.medicaltrans.base.dbsource.DbSourceRuleRspIce>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        MtDbSourceRuleServiceIcePrxHelper.__getDbSourceRuleByGroupId_completed(this, __result);
    }
}
