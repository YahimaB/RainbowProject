//
//  HSFileAccessChecker.h
//  HSShared
//
//  Created by Kyle Hankinson on 2017-01-25.
//  Copyright © 2017 Hankinsoft Development, Inc. All rights reserved.
//

#ifndef HSFileAccessChecker_h
#define HSFileAccessChecker_h

typedef enum
{
    ReadAccess = R_OK,
    WriteAccess = W_OK,
    ExecuteAccess = X_OK,
    PathExists = F_OK
} AccessKind;

static inline BOOL isPathAccessible(NSString *path, AccessKind mode)
{
    return access([path UTF8String], mode) == 0;
}

#endif /* HSFileAccessChecker_h */
