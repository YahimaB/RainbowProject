//
//  HSTableResources.h
//  HSShared
//
//  Created by Kyle Hankinson on 2015-03-06.
//  Copyright (c) 2015 Hankinsoft Development, Inc. All rights reserved.
//

#import <Foundation/Foundation.h> 

#if TARGET_OS_IPHONE
@import UIKit;
#elif TARGET_OS_MAC
@import Foundation;
#endif

#import "HSColor.h"

@interface HSTableResources : NSObject

+ (HSColor*)            primaryKeyColor;

+ (NSAttributedString*) selectedNullAttributedString;
+ (NSAttributedString*) nullAttributedString;

@end
