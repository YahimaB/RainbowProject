//
//  UIView+FindFirstResponder.h
//  HSCore
//
//  Created by Kyle Hankinson on 2016-12-02.
//  Copyright © 2016 Hankinsoft Development, Inc. All rights reserved.
//

#if	TARGET_OS_IPHONE

#import <UIKit/UIKit.h>

@interface UIView (FindFirstResponder)

- (id) findFirstResponder __attribute((deprecated(("Use UIResponder+currentFirstResponder instead as that finds view controllers as well."))));

@end

#endif
