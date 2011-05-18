//
//  Aluno.h
//  Inscricao
//
//  Created by Cleverson Sacramento on 18/05/11.
//  Copyright 2011 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Aluno : NSObject {
}

@property (assign) NSString *nome;

@property (assign) NSString *cpf;

- (id)initWithNome:(NSString *) nome andCpf: (NSString *) cpf;

@end
