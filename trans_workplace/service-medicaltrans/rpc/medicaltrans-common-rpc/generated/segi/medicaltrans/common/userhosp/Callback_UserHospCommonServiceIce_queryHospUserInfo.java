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
// Generated from file `mt_userhosp_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.userhosp;

public abstract class Callback_UserHospCommonServiceIce_queryHospUserInfo
    extends IceInternal.TwowayCallback implements Ice.TwowayCallbackArg1<segi.medicaltrans.common.userhosp.UserHospRelReturnInfoIce>
{
    public final void __completed(Ice.AsyncResult __result)
    {
        UserHospCommonServiceIcePrxHelper.__queryHospUserInfo_completed(this, __result);
    }
}
