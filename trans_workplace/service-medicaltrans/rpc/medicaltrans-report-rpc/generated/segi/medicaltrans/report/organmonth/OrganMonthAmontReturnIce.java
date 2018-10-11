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
// Generated from file `organMonthAmount.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.organmonth;

public class OrganMonthAmontReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public String msg;

    public String
    getMsg()
    {
        return msg;
    }

    public void
    setMsg(String _msg)
    {
        msg = _msg;
    }

    public java.util.List<OrganMonthAmontIce> organMonthAmontIceList;

    public java.util.List<OrganMonthAmontIce>
    getOrganMonthAmontIceList()
    {
        return organMonthAmontIceList;
    }

    public void
    setOrganMonthAmontIceList(java.util.List<OrganMonthAmontIce> _organMonthAmontIceList)
    {
        organMonthAmontIceList = _organMonthAmontIceList;
    }

    public OrganMonthAmontReturnIce()
    {
        code = "";
        msg = "";
    }

    public OrganMonthAmontReturnIce(String code, String msg, java.util.List<OrganMonthAmontIce> organMonthAmontIceList)
    {
        this.code = code;
        this.msg = msg;
        this.organMonthAmontIceList = organMonthAmontIceList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        OrganMonthAmontReturnIce _r = null;
        if(rhs instanceof OrganMonthAmontReturnIce)
        {
            _r = (OrganMonthAmontReturnIce)rhs;
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
            if(msg != _r.msg)
            {
                if(msg == null || _r.msg == null || !msg.equals(_r.msg))
                {
                    return false;
                }
            }
            if(organMonthAmontIceList != _r.organMonthAmontIceList)
            {
                if(organMonthAmontIceList == null || _r.organMonthAmontIceList == null || !organMonthAmontIceList.equals(_r.organMonthAmontIceList))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::organmonth::OrganMonthAmontReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, msg);
        __h = IceInternal.HashUtil.hashAdd(__h, organMonthAmontIceList);
        return __h;
    }

    public OrganMonthAmontReturnIce
    clone()
    {
        OrganMonthAmontReturnIce c = null;
        try
        {
            c = (OrganMonthAmontReturnIce)super.clone();
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
        __os.writeString(msg);
        OrganMonthAmontIceListHelper.write(__os, organMonthAmontIceList);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        msg = __is.readString();
        organMonthAmontIceList = OrganMonthAmontIceListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, OrganMonthAmontReturnIce __v)
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

    static public OrganMonthAmontReturnIce
    __read(IceInternal.BasicStream __is, OrganMonthAmontReturnIce __v)
    {
        if(__v == null)
        {
             __v = new OrganMonthAmontReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final OrganMonthAmontReturnIce __nullMarshalValue = new OrganMonthAmontReturnIce();

    public static final long serialVersionUID = -109208494L;
}
