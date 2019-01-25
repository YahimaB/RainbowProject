//
//  HSHardwareKeyboardManager.h
//  HSCore
//
//  Created by Kyle Hankinson on 2016-12-07.
//  Copyright © 2016 Hankinsoft Development, Inc. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface HSHardwareKeyboardManager : NSObject

+ (instancetype) sharedInstance;

@property(nonatomic,assign,readonly) BOOL isKeyboardAttached;

@end
