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

public class TransProfileReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public TransProfileIce transProfile;

    public TransProfileIce
    getTransProfile()
    {
        return transProfile;
    }

    public void
    setTransProfile(TransProfileIce _transProfile)
    {
        transProfile = _transProfile;
    }

    public TransProfileReturnIce()
    {
        code = "";
        msg = "";
        transProfile = new TransProfileIce();
    }

    public TransProfileReturnIce(String code, String msg, TransProfileIce transProfile)
    {
        this.code = code;
        this.msg = msg;
        this.transProfile = transProfile;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TransProfileReturnIce _r = null;
        if(rhs instanceof TransProfileReturnIce)
        {
            _r = (TransProfileReturnIce)rhs;
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
            if(transProfile != _r.transProfile)
            {
                if(transProfile == null || _r.transProfile == null || !transProfile.equals(_r.transProfile))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::organmonth::TransProfileReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, msg);
        __h = IceInternal.HashUtil.hashAdd(__h, transProfile);
        return __h;
    }

    public TransProfileReturnIce
    clone()
    {
        TransProfileReturnIce c = null;
        try
        {
            c = (TransProfileReturnIce)super.clone();
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
        TransProfileIce.__write(__os, transProfile);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        msg = __is.readString();
        transProfile = TransProfileIce.__read(__is, transProfile);
    }

    static public void
    __write(IceInternal.BasicStream __os, TransProfileReturnIce __v)
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

    static public TransProfileReturnIce
    __read(IceInternal.BasicStream __is, TransProfileReturnIce __v)
    {
        if(__v == null)
        {
             __v = new TransProfileReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TransProfileReturnIce __nullMarshalValue = new TransProfileReturnIce();

    public static final long serialVersionUID = 182116287L;
}
