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

public class UnitSimpleReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public UnitSimpleIce data;

    public UnitSimpleIce
    getData()
    {
        return data;
    }

    public void
    setData(UnitSimpleIce _data)
    {
        data = _data;
    }

    public UnitSimpleReturnIce()
    {
        code = "";
        message = "";
        data = new UnitSimpleIce();
    }

    public UnitSimpleReturnIce(String code, String message, UnitSimpleIce data)
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
        UnitSimpleReturnIce _r = null;
        if(rhs instanceof UnitSimpleReturnIce)
        {
            _r = (UnitSimpleReturnIce)rhs;
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::spacemanage::UnitSimpleReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, data);
        return __h;
    }

    public UnitSimpleReturnIce
    clone()
    {
        UnitSimpleReturnIce c = null;
        try
        {
            c = (UnitSimpleReturnIce)super.clone();
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
        UnitSimpleIce.__write(__os, data);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        data = UnitSimpleIce.__read(__is, data);
    }

    static public void
    __write(IceInternal.BasicStream __os, UnitSimpleReturnIce __v)
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

    static public UnitSimpleReturnIce
    __read(IceInternal.BasicStream __is, UnitSimpleReturnIce __v)
    {
        if(__v == null)
        {
             __v = new UnitSimpleReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UnitSimpleReturnIce __nullMarshalValue = new UnitSimpleReturnIce();

    public static final long serialVersionUID = -1607831058L;
}
