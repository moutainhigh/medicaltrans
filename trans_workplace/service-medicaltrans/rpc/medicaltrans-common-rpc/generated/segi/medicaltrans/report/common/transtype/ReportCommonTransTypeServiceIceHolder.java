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

public final class ReportCommonTransTypeServiceIceHolder extends Ice.ObjectHolderBase<ReportCommonTransTypeServiceIce>
{
    public
    ReportCommonTransTypeServiceIceHolder()
    {
    }

    public
    ReportCommonTransTypeServiceIceHolder(ReportCommonTransTypeServiceIce value)
    {
        this.value = value;
    }

    public void
    patch(Ice.Object v)
    {
        if(v == null || v instanceof ReportCommonTransTypeServiceIce)
        {
            value = (ReportCommonTransTypeServiceIce)v;
        }
        else
        {
            IceInternal.Ex.throwUOE(type(), v);
        }
    }

    public String
    type()
    {
        return _ReportCommonTransTypeServiceIceDisp.ice_staticId();
    }
}
