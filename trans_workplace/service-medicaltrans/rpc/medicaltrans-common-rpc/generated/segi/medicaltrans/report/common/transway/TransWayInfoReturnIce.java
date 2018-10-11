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
// Generated from file `mt_transway_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.report.common.transway;

public class TransWayInfoReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public TransWayInfo data;

    public TransWayInfo
    getData()
    {
        return data;
    }

    public void
    setData(TransWayInfo _data)
    {
        data = _data;
    }

    public TransWayInfoReturnIce()
    {
        code = "";
        msg = "";
        data = new TransWayInfo();
    }

    public TransWayInfoReturnIce(String code, String msg, TransWayInfo data)
    {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TransWayInfoReturnIce _r = null;
        if(rhs instanceof TransWayInfoReturnIce)
        {
            _r = (TransWayInfoReturnIce)rhs;
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
            if(data != _r.data)
            {
                if(data == null || _r.data == null || !data.equals(_r.data))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::report::common::transway::TransWayInfoReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, msg);
        __h = IceInternal.HashUtil.hashAdd(__h, data);
        return __h;
    }

    public TransWayInfoReturnIce
    clone()
    {
        TransWayInfoReturnIce c = null;
        try
        {
            c = (TransWayInfoReturnIce)super.clone();
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
        TransWayInfo.__write(__os, data);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        msg = __is.readString();
        data = TransWayInfo.__read(__is, data);
    }

    static public void
    __write(IceInternal.BasicStream __os, TransWayInfoReturnIce __v)
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

    static public TransWayInfoReturnIce
    __read(IceInternal.BasicStream __is, TransWayInfoReturnIce __v)
    {
        if(__v == null)
        {
             __v = new TransWayInfoReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TransWayInfoReturnIce __nullMarshalValue = new TransWayInfoReturnIce();

    public static final long serialVersionUID = 1362559721L;
}
