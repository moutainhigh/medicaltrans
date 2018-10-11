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
// Generated from file `userposit.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.userposit;

public class UserPositReturnIce implements java.lang.Cloneable, java.io.Serializable
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

    public UserPositRetIce userPositRetIce;

    public UserPositRetIce
    getUserPositRetIce()
    {
        return userPositRetIce;
    }

    public void
    setUserPositRetIce(UserPositRetIce _userPositRetIce)
    {
        userPositRetIce = _userPositRetIce;
    }

    public UserPositReturnIce()
    {
        code = "";
        message = "";
        userPositRetIce = new UserPositRetIce();
    }

    public UserPositReturnIce(String code, String message, UserPositRetIce userPositRetIce)
    {
        this.code = code;
        this.message = message;
        this.userPositRetIce = userPositRetIce;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        UserPositReturnIce _r = null;
        if(rhs instanceof UserPositReturnIce)
        {
            _r = (UserPositReturnIce)rhs;
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
            if(userPositRetIce != _r.userPositRetIce)
            {
                if(userPositRetIce == null || _r.userPositRetIce == null || !userPositRetIce.equals(_r.userPositRetIce))
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
        __h = IceInternal.HashUtil.hashAdd(__h, "::segi::medicaltrans::base::userposit::UserPositReturnIce");
        __h = IceInternal.HashUtil.hashAdd(__h, code);
        __h = IceInternal.HashUtil.hashAdd(__h, message);
        __h = IceInternal.HashUtil.hashAdd(__h, userPositRetIce);
        return __h;
    }

    public UserPositReturnIce
    clone()
    {
        UserPositReturnIce c = null;
        try
        {
            c = (UserPositReturnIce)super.clone();
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
        UserPositRetIce.__write(__os, userPositRetIce);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
        code = __is.readString();
        message = __is.readString();
        userPositRetIce = UserPositRetIce.__read(__is, userPositRetIce);
    }

    static public void
    __write(IceInternal.BasicStream __os, UserPositReturnIce __v)
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

    static public UserPositReturnIce
    __read(IceInternal.BasicStream __is, UserPositReturnIce __v)
    {
        if(__v == null)
        {
             __v = new UserPositReturnIce();
        }
        __v.__read(__is);
        return __v;
    }
    
    private static final UserPositReturnIce __nullMarshalValue = new UserPositReturnIce();

    public static final long serialVersionUID = -1671530017L;
}
