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
// Generated from file `userinfomanage.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.userinfomanage;

public class UserInfoIceRespIce implements java.lang.Cloneable, java.io.Serializable
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

    public java.util.List<UserInfoIce> resultList;

    public java.util.List<UserInfoIce>
    getResultList()
    {
        return resultList;
    }

    public void
    setResultList(java.util.List<UserInfoIce> _resultList)
    {
        resultList = _resultList;
    }

    public UserInfoIceRespIce()
    {
        code = "";
        message = "";
    }

    public UserInfoIceRespIce(String code, String message, page.RpcPageIce paginator, java.util.List<UserInfoIce> resultList)
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
        UserInfoIceRespIce _r = null;
        if(rhs instanceof UserInfoIceRespIce)
        {
            _r = (UserInfoIceRespIce)rhs;
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::userinfomanage::UserInfoIceRespIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, paginator);
        __h = IceInternal.HashUtil.hashAdd(__h, resultList);
        return __h;
    }

    public UserInfoIceRespIce
    clone()
    {
        UserInfoIceRespIce c = null;
        try
        {
            c = (UserInfoIceRespIce)super.clone();
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
        UserInfoIceListHelper.write(__os, resultList);
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
        resultList = UserInfoIceListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, UserInfoIceRespIce __v)
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

    static public UserInfoIceRespIce
    __read(IceInternal.BasicStream __is, UserInfoIceRespIce __v)
    {
        if(__v == null)
        {
             __v = new UserInfoIceRespIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserInfoIceRespIce __nullMarshalValue = new UserInfoIceRespIce();

    public static final long serialVersionUID = 181238038L;
}
