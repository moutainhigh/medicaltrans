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
// Generated from file `mt_tasktype_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.transType;

public class TransTypeInfoReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public java.util.List<TransTypeInfo> resultList;

    public java.util.List<TransTypeInfo>
    getResultList()
    {
        return resultList;
    }

    public void
    setResultList(java.util.List<TransTypeInfo> _resultList)
    {
        resultList = _resultList;
    }

    public TransTypeInfoReturnIce()
    {
        code = "";
        message = "";
    }

    public TransTypeInfoReturnIce(String code, String message, java.util.List<TransTypeInfo> resultList)
    {
        this.code = code;
        this.message = message;
        this.resultList = resultList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TransTypeInfoReturnIce _r = null;
        if(rhs instanceof TransTypeInfoReturnIce)
        {
            _r = (TransTypeInfoReturnIce)rhs;
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
            if(resultList != _r.resultList)
            {
                if(resultList == null || _r.resultList == null || !resultList.equals(_r.resultList))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::common::transType::TransTypeInfoReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, resultList);
        return __h;
    }

    public TransTypeInfoReturnIce
    clone()
    {
        TransTypeInfoReturnIce c = null;
        try
        {
            c = (TransTypeInfoReturnIce)super.clone();
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
        TransTypeInfoListHelper.write(__os, resultList);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        resultList = TransTypeInfoListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, TransTypeInfoReturnIce __v)
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

    static public TransTypeInfoReturnIce
    __read(IceInternal.BasicStream __is, TransTypeInfoReturnIce __v)
    {
        if(__v == null)
        {
             __v = new TransTypeInfoReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final TransTypeInfoReturnIce __nullMarshalValue = new TransTypeInfoReturnIce();

    public static final long serialVersionUID = 1154154318L;
}
