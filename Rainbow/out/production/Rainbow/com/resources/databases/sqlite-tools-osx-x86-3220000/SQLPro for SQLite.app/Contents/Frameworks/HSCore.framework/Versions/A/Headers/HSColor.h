//
//  HSColor.h
//  HSCore
//
//  Created by Kyle Hankinson on 2017-01-10.
//  Copyright © 2017 Hankinsoft Development, Inc. All rights reserved.
//

#ifndef HSColor_h
#define HSColor_h

@import Foundation;

#if TARGET_OS_IPHONE
@import UIKit;
#elif TARGET_OS_MAC
@import AppKit;
#endif

#if TARGET_OS_IPHONE
typedef UIColor HSColor;
#else
typedef NSColor HSColor;
#endif

#endif /* HSColor_h */
