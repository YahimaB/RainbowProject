//
//  HSDataTypeDetector.h
//  HSCore
//
//  Created by Kyle Hankinson on 9/5/17.
//  Copyright © 2017 Hankinsoft Development, Inc. All rights reserved.
//

#import <Foundation/Foundation.h>

typedef enum
{
    HSDetectedDataTypeUnknown = 0,      // Unknown
    HSDetectedDataTypeJPG,       // joint photographic experts group - .jpeg or .jpg
    HSDetectedDataTypePNG,        // portable network graphics
    HSDetectedDataTypeGIF,       // graphics interchange format
    HSDetectedDataTypeTIFF,      // tagged image file format
    HSDetectedDataTypeBMP,       // Microsoft bitmap format
    HSDetectedDataTypeWEBP,      // Google WebP format, a type of .riff file
    HSDetectedDataTypeICO,       // Microsoft icon format,
    HSDetectedDataTypePDF,       // PDF
} HSDetectedDataType;

@interface HSDataTypeDetector : NSObject

+ (instancetype) sharedInstance;
+ (BOOL) isDetectedDataTypeImage: (HSDetectedDataType) detectedDataType;
+ (NSString*) defaultExtensionForDataType: (HSDetectedDataType) detectedDataType;
+ (NSString*) displayForData: (NSData*) data;

- (HSDetectedDataType) dataTypeForData: (NSData*) data;

@end
