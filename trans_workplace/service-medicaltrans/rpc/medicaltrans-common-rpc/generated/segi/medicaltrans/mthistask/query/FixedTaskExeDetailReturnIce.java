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
// Generated from file `mt_his_task_query.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.mthistask.query;

public class FixedTaskExeDetailReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public FixedTaskExeDetailIce data;

    public FixedTaskExeDetailIce
    getData()
    {
        return data;
    }

    public void
    setData(FixedTaskExeDetailIce _data)
    {
        data = _data;
    }

    public FixedTaskExeDetailReturnIce()
    {
        code = "";
        message = "";
        data = new FixedTaskExeDetailIce();
    }

    public FixedTaskExeDetailReturnIce(String code, String message, FixedTaskExeDetailIce data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        FixedTaskExeDetailReturnIce _r = null;
        if(rhs instanceof FixedTaskExeDetailReturnIce)
        {
            _r = (FixedTaskExeDetailReturnIce)rhs;
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::mthistask::query::FixedTaskExeDetailReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, data);
        return __h;
    }

    public FixedTaskExeDetailReturnIce
    clone()
    {
        FixedTaskExeDetailReturnIce c = null;
        try
        {
            c = (FixedTaskExeDetailReturnIce)super.clone();
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
        FixedTaskExeDetailIce.__write(__os, data);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        data = FixedTaskExeDetailIce.__read(__is, data);
    }

    static public void
    __write(IceInternal.BasicStream __os, FixedTaskExeDetailReturnIce __v)
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

    static public FixedTaskExeDetailReturnIce
    __read(IceInternal.BasicStream __is, FixedTaskExeDetailReturnIce __v)
    {
        if(__v == null)
        {
             __v = new FixedTaskExeDetailReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final FixedTaskExeDetailReturnIce __nullMarshalValue = new FixedTaskExeDetailReturnIce();

    public static final long serialVersionUID = 1876392456L;
}
