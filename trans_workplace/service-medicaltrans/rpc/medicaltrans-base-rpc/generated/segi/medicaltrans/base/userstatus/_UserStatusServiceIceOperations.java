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
// Generated from file `userstatus.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package segi.medicaltrans.base.userstatus;

public interface _UserStatusServiceIceOperations
{
    UserStatusReturnIce updateStausByUser(UserStatusIce userStatusIce, Ice.Current __current);

    UserStatusReturnIce getStatusByUser(UserStatusIce userStatusIce, Ice.Current __current);
}
