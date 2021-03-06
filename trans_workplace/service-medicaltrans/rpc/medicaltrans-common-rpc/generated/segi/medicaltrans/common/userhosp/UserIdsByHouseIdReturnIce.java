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
// Generated from file `mt_userhosp_common.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.common.userhosp;

public class UserIdsByHouseIdReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public java.util.List<java.lang.String> userIdList;

    public java.util.List<java.lang.String>
    getUserIdList()
    {
        return userIdList;
    }

    public void
    setUserIdList(java.util.List<java.lang.String> _userIdList)
    {
        userIdList = _userIdList;
    }

    public UserIdsByHouseIdReturnIce()
    {
        code = "";
        message = "";
    }

    public UserIdsByHouseIdReturnIce(String code, String message, java.util.List<java.lang.String> userIdList)
    {
        this.code = code;
        this.message = message;
        this.userIdList = userIdList;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserIdsByHouseIdReturnIce _r = null;
        if(rhs instanceof UserIdsByHouseIdReturnIce)
        {
            _r = (UserIdsByHouseIdReturnIce)rhs;
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
            if(userIdList != _r.userIdList)
            {
                if(userIdList == null || _r.userIdList == null || !userIdList.equals(_r.userIdList))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::common::userhosp::UserIdsByHouseIdReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, userIdList);
        return __h;
    }

    public UserIdsByHouseIdReturnIce
    clone()
    {
        UserIdsByHouseIdReturnIce c = null;
        try
        {
            c = (UserIdsByHouseIdReturnIce)super.clone();
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
        UserIdListHelper.write(__os, userIdList);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        userIdList = UserIdListHelper.read(__is);
    }

    static public void
    __write(IceInternal.BasicStream __os, UserIdsByHouseIdReturnIce __v)
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

    static public UserIdsByHouseIdReturnIce
    __read(IceInternal.BasicStream __is, UserIdsByHouseIdReturnIce __v)
    {
        if(__v == null)
        {
             __v = new UserIdsByHouseIdReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserIdsByHouseIdReturnIce __nullMarshalValue = new UserIdsByHouseIdReturnIce();

    public static final long serialVersionUID = 2096574973L;
}
