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

public final class UserHospCommonServiceIceHolder extends Ice.ObjectHolderBase<UserHospCommonServiceIce>
{
    public
    UserHospCommonServiceIceHolder()
    {
    }

    public
    UserHospCommonServiceIceHolder(UserHospCommonServiceIce value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof UserHospCommonServiceIce)
        {
            value = (UserHospCommonServiceIce)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _UserHospCommonServiceIceDisp.ice_staticId();
    }
}
