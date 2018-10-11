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
// Generated from file `mt_report_organ.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.report.organ;

public class ReportOrganIce implements java.lang.Cloneable, java.io.Serializable
{
    public String code;

    public String
    getCode()
    {
        return code;
    }

    public void
    setCode(String _code)
    {
        code = _code;
    }

    public String message;

    public String
    getMessage()
    {
        return message;
    }

    public void
    setMessage(String _message)
    {
        message = _message;
    }

    public java.util.List<java.lang.Integer> organIds;

    public java.util.List<java.lang.Integer>
    getOrganIds()
    {
        return organIds;
    }

    public void
    setOrganIds(java.util.List<java.lang.Integer> _organIds)
    {
        organIds = _organIds;
    }

    public ReportOrganIce()
    {
        code = "";
        message = "";
    }

    public ReportOrganIce(String code, String message, java.util.List<java.lang.Integer> organIds)
    {
        this.code = code;
        this.message = message;
        this.organIds = organIds;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        ReportOrganIce _r = null;
        if(rhs instanceof ReportOrganIce)
        {
            _r = (ReportOrganIce)rhs;
        }

        if(_r != null)
        {
            if(code != _r.code)
            {
                if(code == null || _r.code == null || !code.equals(_r.code))
                {
                    return false;
                }
            }
            if(message != _r.message)
            {
                if(message == null || _r.message == null || !message.equals(_r.message))
                {
                    return false;
                }
            }
            if(organIds != _r.organIds)
            {
                if(organIds == null || _r.organIds == null || !organIds.equals(_r.organIds))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int
    hashCode()
    {
        int __h = 5381;
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::common::report::organ::ReportOrganIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, organIds);
        return __h;
    }

    public ReportOrganIce
    clone()
    {
        ReportOrganIce c = null;
        try
        {
            c = (ReportOrganIce)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
        __os.writeString(code);
        __os.writeString(message);
        OrganIdListHelper.write(__os, organIds);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        organIds = OrganIdListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, ReportOrganIce __v)
    {
        if(__v == null)
        {
            __nullMarshalValue.__write(__os);
        }
        else
        {
            __v.__write(__os);
        }
    }

    static public ReportOrganIce
    __read(IceInternal.BasicStream __is, ReportOrganIce __v)
    {
        if(__v == null)
        {
             __v = new ReportOrganIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final ReportOrganIce __nullMarshalValue = new ReportOrganIce();

    public static final long serialVersionUID = -171201174L;
}
