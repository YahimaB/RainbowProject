//
//  HSImage.h
//  HSCore
//
//  Created by Kyle Hankinson on 8/18/17.
//  Copyright © 2017 Hankinsoft Development, Inc. All rights reserved.
//

#ifndef HSImage_h
#define HSImage_h

@import Foundation;

#if TARGET_OS_IPHONE
@import UIKit;
#elif TARGET_OS_MAC
@import AppKit;
#endif

#if TARGET_OS_IPHONE
typedef UIImage HSImage;
#else
typedef NSImage HSImage;
#endif

#endif /* HSImage_h */
