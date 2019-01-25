//
//  NSString+Hankinsoft.h
//  SQL Server Professional
//
//  Created by Kyle Hankinson on 2013-06-06.
//  Copyright (c) 2013 Hankinsoft. All rights reserved.
//

@import Foundation;

@interface NSString (Hankinsoft)

+ (NSString *) stringFromSeconds: (NSTimeInterval)seconds
             includeMillaseconds: (BOOL) includeMillaseconds;

@end
