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
// Generated from file `transtype.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.transtype;

public class TransTypeAllListIce implements java.lang.Cloneable, java.io.Serializable
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

    public java.util.List<TransTypeRetIce> transTypeList;

    public java.util.List<TransTypeRetIce>
    getTransTypeList()
    {
        return transTypeList;
    }

    public void
    setTransTypeList(java.util.List<TransTypeRetIce> _transTypeList)
    {
        transTypeList = _transTypeList;
    }

    public TransTypeAllListIce()
    {
        code = "";
        message = "";
    }

    public TransTypeAllListIce(String code, String message, java.util.List<TransTypeRetIce> transTypeList)
    {
        this.code = code;
        this.message = message;
        this.transTypeList = transTypeList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TransTypeAllListIce _r = null;
        if(rhs instanceof TransTypeAllListIce)
        {
            _r = (TransTypeAllListIce)rhs;
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
            if(transTypeList != _r.transTypeList)
            {
                if(transTypeList == null || _r.transTypeList == null || !transTypeList.equals(_r.transTypeList))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::transtype::TransTypeAllListIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, transTypeList);
        return __h;
    }

    public TransTypeAllListIce
    clone()
    {
        TransTypeAllListIce c = null;
        try
        {
            c = (TransTypeAllListIce)super.clone();
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
        TransTypeRetListIceHelper.write(__os, transTypeList);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        transTypeList = TransTypeRetListIceHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, TransTypeAllListIce __v)
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

    static public TransTypeAllListIce
    __read(IceInternal.BasicStream __is, TransTypeAllListIce __v)
    {
        if(__v == null)
        {
             __v = new TransTypeAllListIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TransTypeAllListIce __nullMarshalValue = new TransTypeAllListIce();

    public static final long serialVersionUID = -821977510L;
}
