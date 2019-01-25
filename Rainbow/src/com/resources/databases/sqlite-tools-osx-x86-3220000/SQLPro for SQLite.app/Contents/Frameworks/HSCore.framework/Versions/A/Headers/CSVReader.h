//
//  CSVReader.h
//  SQLite Professional
//
//  Created by Kyle Hankinson on 2013-10-31.
//  Copyright (c) 2013 Hankinsoft. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "CHCSVParser.h"
#import "HSProgressBlock.h"

typedef void(^CSVReaderSuccessBlock)(NSArray * results);
typedef void(^CSVReaderFailureBlock)(NSError * error);

@interface CSVReader : NSObject <CHCSVParserDelegate>
{
    
}

@property (nonatomic, copy) HSProgressBlock         progressBlock;

@property (nonatomic, copy) CSVReaderSuccessBlock   successBlock;
@property (nonatomic, copy) CSVReaderFailureBlock   failureBlock;

@end
