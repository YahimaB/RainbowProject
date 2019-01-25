//
//  HSPowerHelper.h
//  HSShared
//
//  Created by Kyle Hankinson on 2015-07-09.
//  Copyright (c) 2015 Hankinsoft Development, Inc. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface HSPowerManagementHelper : NSObject

+ (HSPowerManagementHelper*) sharedInstance;

- (void) increase;
- (void) decrease;

@end
