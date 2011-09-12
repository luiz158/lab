//
//  CasaJaMarcadaException.m
//  JogoDaVelha
//
//  Created by Cleverson Sacramento on 12/09/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "CasaJaMarcadaException.h"

@implementation CasaJaMarcadaException

- (id)init {
    self = [super initWithName:@"Esta casa já foi marcada!" reason:nil userInfo:nil];
    if (self) {
        // Initialization code here.
    }
    
    return self;
}

@end
