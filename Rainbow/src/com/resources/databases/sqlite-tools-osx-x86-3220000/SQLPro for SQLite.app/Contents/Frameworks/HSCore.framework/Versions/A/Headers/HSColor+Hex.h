//
//  NSColor+Hex.h
//  Coder
//
//  Created by Kyle Hankinson on 2014-05-05.
//  Copyright (c) 2014 com.hankinsoft.osx. All rights reserved.
//

#import "HSColor.h"

#if !TARGET_OS_IPHONE && TARGET_OS_MAC

@interface NSColor (Hex)

- (NSString *) hexadecimalValue;

@end

#elif TARGET_OS_IPHONE

@interface UIColor (Hex)

- (NSString *) hexadecimalValue;
- (UIImage *) solidImageWithWidth: (CGFloat) width
                           height: (CGFloat) height;

- (UIColor*) inverseColor;

@end

#endif
