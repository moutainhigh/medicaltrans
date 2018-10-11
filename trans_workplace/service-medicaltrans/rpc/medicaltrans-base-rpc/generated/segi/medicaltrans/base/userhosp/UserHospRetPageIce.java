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
// Generated from file `mt_userhosp.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.userhosp;

public class UserHospRetPageIce implements java.lang.Cloneable, java.io.Serializable
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

    public page.RpcPageIce paginator;

    public page.RpcPageIce
    getPaginator()
    {
        return paginator;
    }

    public void
    setPaginator(page.RpcPageIce _paginator)
    {
        paginator = _paginator;
    }

    public java.util.List<UserHospIce> resultList;

    public java.util.List<UserHospIce>
    getResultList()
    {
        return resultList;
    }

    public void
    setResultList(java.util.List<UserHospIce> _resultList)
    {
        resultList = _resultList;
    }

    public UserHospRetPageIce()
    {
        code = "";
        message = "";
    }

    public UserHospRetPageIce(String code, String message, page.RpcPageIce paginator, java.util.List<UserHospIce> resultList)
    {
        this.code = code;
        this.message = message;
        this.paginator = paginator;
        this.resultList = resultList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserHospRetPageIce _r = null;
        if(rhs instanceof UserHospRetPageIce)
        {
            _r = (UserHospRetPageIce)rhs;
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
            if(paginator != _r.paginator)
            {
                if(paginator == null || _r.paginator == null || !paginator.equals(_r.paginator))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::userhosp::UserHospRetPageIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, paginator);
        __h = IceInternal.HashUtil.hashAdd(__h, resultList);
        return __h;
    }

    public UserHospRetPageIce
    clone()
    {
        UserHospRetPageIce c = null;
        try
        {
            c = (UserHospRetPageIce)super.clone();
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
        __os.writeObject(paginator);
        UserHospIceListHelper.write(__os, resultList);
    }

    private class Patcher implements IceInternal.Patcher
    {
        public void
        patch(Ice.Object v)
        {
            if(v == null || v instanceof page.RpcPageIce)
            {
                paginator = (page.RpcPageIce)v;
            }
            else
            {
                IceInternal.Ex.throwUOE(type(), v);
            }
        }

        public String
        type()
        {
            return "::page::RpcPageIce";
        }
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        __is.readObject(new Patcher());
        resultList = UserHospIceListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, UserHospRetPageIce __v)
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

    static public UserHospRetPageIce
    __read(IceInternal.BasicStream __is, UserHospRetPageIce __v)
    {
        if(__v == null)
        {
             __v = new UserHospRetPageIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserHospRetPageIce __nullMarshalValue = new UserHospRetPageIce();

    public static final long serialVersionUID = 706314044L;
}
