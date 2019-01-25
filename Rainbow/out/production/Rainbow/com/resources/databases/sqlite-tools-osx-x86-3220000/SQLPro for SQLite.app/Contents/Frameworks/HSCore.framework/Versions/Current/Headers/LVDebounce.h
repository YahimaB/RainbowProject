//
//  LVDebounce.h
//  HSShared
//
//  Created by Kyle Hankinson on 2015-07-23.
//  Copyright (c) 2015 Hankinsoft Development, Inc. All rights reserved.
//

@import Foundation;

@interface LVDebounce : NSObject

+ (void) fireAfter: (NSTimeInterval)seconds
            target: (id)target
          selector: (SEL)aSelector
          userInfo: (id)userInfo;

@end