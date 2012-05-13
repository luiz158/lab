//  Monitoramento.h

#import <Foundation/Foundation.h>

@interface Monitoramento : NSObject

// Código dos Correios que referente ao objeto monitorado
@property NSString* objeto;

// E-mail que será notificado a cada mudança no status do objeto
@property NSString* email;

@end
