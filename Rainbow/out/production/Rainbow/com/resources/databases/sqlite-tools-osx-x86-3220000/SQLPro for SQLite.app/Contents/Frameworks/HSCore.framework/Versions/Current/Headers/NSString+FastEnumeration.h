//
//  NSString+FastEnumeration.h
//  HSShared
//
//  Created by Kyle Hankinson on 2014-09-24.
//  Copyright (c) 2014 Hankinsoft Development, Inc. All rights reserved.
//

@import Foundation;

@interface NSString(FastEnumeration)

-(void) enumerateCharactersWithBlock:(void (^)(unichar previousCharacter,
                                               unichar currentCharacter,
                                               unichar nextCharacter,
                                               NSUInteger index,
                                               BOOL * stop))block;

@end
