//
//  AlunoDuplicadoException.m
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import "AlunoDuplicadoException.h"


@implementation AlunoDuplicadoException

- (id) initWithAluno:(NSString *) aAluno {
    NSString *message = [@"Aluno duplicado: " stringByAppendingString:aAluno];
    self = [super initWithName:@"AlunoDuplicadoException" reason:message userInfo:nil];
    [message release];
    
    if (self) {
        aluno = aAluno;
    }
    
    return self;
}

@end
