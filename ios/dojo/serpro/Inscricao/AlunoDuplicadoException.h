//
//  AlunoDuplicadoException.h
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface AlunoDuplicadoException : NSException {

    NSString *aluno;
}

- (id) initWithAluno:(NSString *) aluno;

@end
