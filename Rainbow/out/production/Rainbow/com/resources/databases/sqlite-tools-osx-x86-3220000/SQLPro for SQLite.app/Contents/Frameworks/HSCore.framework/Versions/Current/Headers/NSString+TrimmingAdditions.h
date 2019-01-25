//
//  NSString+TrimmingAdditions.h
//  HSShared
//
//  Created by Kyle Hankinson on 2016-04-08.
//  Copyright © 2016 Hankinsoft Development, Inc. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface NSString (TrimmingAdditions)

- (NSString *) stringByTrimmingLeadingCharactersInSet: (NSCharacterSet *) characterSet;
- (NSString *) stringByTrimmingTrailingCharactersInSet: (NSCharacterSet *) characterSet;

@end
