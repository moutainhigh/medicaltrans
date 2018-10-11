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
// Generated from file `spacemanage.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.spacemanage;

public class FloorDetailReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public FloorDetailIce data;

    public FloorDetailIce
    getData()
    {
        return data;
    }

    public void
    setData(FloorDetailIce _data)
    {
        data = _data;
    }

    public FloorDetailReturnIce()
    {
        code = "";
        message = "";
        data = new FloorDetailIce();
    }

    public FloorDetailReturnIce(String code, String message, FloorDetailIce data)
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
        FloorDetailReturnIce _r = null;
        if(rhs instanceof FloorDetailReturnIce)
        {
            _r = (FloorDetailReturnIce)rhs;
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::spacemanage::FloorDetailReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, data);
        return __h;
    }

    public FloorDetailReturnIce
    clone()
    {
        FloorDetailReturnIce c = null;
        try
        {
            c = (FloorDetailReturnIce)super.clone();
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
        FloorDetailIce.__write(__os, data);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        data = FloorDetailIce.__read(__is, data);
    }

    static public void
    __write(IceInternal.BasicStream __os, FloorDetailReturnIce __v)
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

    static public FloorDetailReturnIce
    __read(IceInternal.BasicStream __is, FloorDetailReturnIce __v)
    {
        if(__v == null)
        {
             __v = new FloorDetailReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final FloorDetailReturnIce __nullMarshalValue = new FloorDetailReturnIce();

    public static final long serialVersionUID = 19647407L;
}
